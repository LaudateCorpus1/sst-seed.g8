package $package$.tests

import com.avast.sst.doobie.DoobieHikariModule
import $package$.config.Configuration
import $package$.service.RandomService
import com.avast.sst.jvm.execution.ConfigurableThreadFactory.Config
import com.avast.sst.jvm.execution.{ConfigurableThreadFactory, ExecutorModule}
import com.avast.sst.pureconfig.PureConfigModule
import com.avast.sst.doobie.DoobieHikariConfig
import org.scalatest.funsuite.AsyncFunSuite
import cats.effect.Resource
import zio._
import zio.interop.catz._
import scala.concurrent.ExecutionContext
import com.dimafeng.testcontainers.{ForAllTestContainer, PostgreSQLContainer}


class RandomServiceTest extends AsyncFunSuite with ForAllTestContainer {

  // be sure that you have Docker installed to run this
  override val container = PostgreSQLContainer()

  def overrideDbConfiguration(conf: DoobieHikariConfig): DoobieHikariConfig =
    conf.copy(url = container.jdbcUrl, username = container.username, password = container.password)

  test("Random Service should return") {
    val runtime = new DefaultRuntime {}

    val test: Resource[Task, RandomService] = for {
      configuration <- Resource.liftF(PureConfigModule.makeOrRaise[Task, Configuration])
      executorModule <- ExecutorModule.makeFromExecutionContext[Task](runtime.platform.executor.asEC)
      boundedConnectExecutionContext <- executorModule
        .makeThreadPoolExecutor(configuration.boundedConnectExecutor,
                              new ConfigurableThreadFactory(Config(Some("hikari-connect-%02d"))))
                              .map(ExecutionContext.fromExecutorService)
      doobieTransactor <- DoobieHikariModule.make[Task](overrideDbConfiguration(configuration.database),
        boundedConnectExecutionContext,
        executorModule.blocker)
      executorModule <- ExecutorModule.makeFromExecutionContext[Task](runtime.platform.executor.asEC)
      randomService = RandomService(doobieTransactor)
    } yield randomService

    val task: Task[Double] = test
      .use { randomService =>
          randomService.randomNumber
      }

    assert(runtime.unsafeRun(task.fold(_ => 1, _ => 0)) === 0) // is succeed
    assert(runtime.unsafeRun(task).isInstanceOf[Double])

  }
}

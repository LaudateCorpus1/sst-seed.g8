package $package$.tests

import scala.concurrent.ExecutionContext

class RandomServiceTest extends AsyncFunSuite with ForAllTestContainer {

  // Docker must be installed to be able to run this
  override val container = PostgreSQLContainer()

  test("RandomService should return") {
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
        randomService.randomNumber.map(n => assert(n >= 0.0 && n <= 1.0))
      }

    runtime.unsafeRunToFuture(task)
  }

  private def overrideDbConfiguration(config: DoobieHikariConfig): DoobieHikariConfig =
    config.copy(url = container.jdbcUrl, username = container.username, password = container.password)

}

package com.malliina.app

import controllers.{AssetsComponents, Home}
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, LoggerConfigurator}
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes

class AppLoader extends ApplicationLoader {
  override def load(context: Context): Application = {
    val environment = context.environment
    LoggerConfigurator(environment.classLoader)
      .foreach(_.configure(environment))
    new AppComponents(context).application
  }
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with AssetsComponents {
  val demoValue = 42
  val home = new Home(controllerComponents, assets)
  override val router: Router = new Routes(httpErrorHandler, home)
}

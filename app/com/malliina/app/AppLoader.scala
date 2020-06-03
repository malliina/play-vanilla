package com.malliina.app

import controllers.{AssetsComponents, Home}
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, NoHttpFiltersComponents}
import router.Routes

class AppLoader extends ApplicationLoader {
  override def load(context: Context): Application = new AppComponents(context).application
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with NoHttpFiltersComponents
    with AssetsComponents {
  val demoValue = 42
  val home = new Home(controllerComponents, assets)
  override val router: Router = new Routes(httpErrorHandler, home)
}

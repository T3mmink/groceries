package nl.arnovanoort.groceries.controllers

import akka.actor.{Props, ActorRef}
import spray.http.StatusCodes
import spray.routing.HttpService

/**
 * Created by temmink on 25-8-15.
 */
trait Controller extends HttpService {


  def groceriesRoute(actor: ActorRef) = {
    path("list"){
      post{
        respondWithStatus(StatusCodes.Created){
          System.out.println("yessss....")
          actor !  "bla"
          complete{"Grocery list is being processed"}
        }
      }
    }
  }
}

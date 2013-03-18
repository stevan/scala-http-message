package com.iinteractive.http.headers

import scala.collection.mutable.LinkedHashMap

trait WithParams {
    val params: LinkedHashMap[String, String]  = LinkedHashMap()
    
    def addParam(k: String, v: String) = params += (k -> v)
    def removeParam(k: String) = params -= k

    def paramsAreEmpty = params.isEmpty
    def paramsInOrder  = params.toList

    def paramsAreSubset(other: LinkedHashMap[String, String]): Boolean = params.forall {
        case (k, v)   => other.get(k) match {
            // check if it exists
            case Some(ov) => ov == v
            // it is okay if it doesn't exist
            case _        => true 
        }
    }    

    def paramsMatch(other: LinkedHashMap[String, String]): Boolean = {
        if (params.size != other.size) {
            return false 
        } else {
            return params.forall {
                case (k, v)   => other.get(k) match {
                    case Some(ov) => ov == v
                    case _        => false 
                }
            }    
        }
    }

    def paramsToString = params.map(p => p._1 + "=\"" + p._2 + "\"").mkString("; ")
}
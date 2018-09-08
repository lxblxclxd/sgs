package com.seu.biz.rule

import com.seu.biz.event.IGameEvent
import com.seu.biz.table.ITable

class GamePhase(val table: ITable, val name: String, val logName: String? = null) {
    //var innerPhases: ArrayList<GamePhase> = ArrayList()
    var startActions: ArrayList<IGameEvent> = ArrayList()
    var processActions: ArrayList<IGameEvent> = ArrayList()
    var endActions: ArrayList<IGameEvent> = ArrayList()

    fun execute() {
        table.phaseStack.push(this)
        println("${logName}开始")
        startActions.forEach { e -> table.log.recordAndPrint(e.execute()) }
        while (processActions.size > 0) {
            table.log.recordAndPrint(processActions.first().execute())
            processActions.removeAt(0)
        }

        //processActions.forEach { e -> table.log.recordAndPrint(e.execute()) }

        //innerPhases.forEach { i -> i.execute() }
        endActions.forEach { e -> table.log.recordAndPrint(e.execute()) }
        println("${logName}结束")
        table.phaseStack.pop()
    }

    fun registerOnStart(action: IGameEvent) {
        startActions.add(action)
    }

    fun registerOnProcess(action: IGameEvent) {
        processActions.add(action)
    }

    /*fun registerInnerPhase(inner: GamePhase) {
        innerPhases.add(inner)
    }*/

    fun registerOnEnd(action: IGameEvent) {
        endActions.add(action)
    }

    fun getUpperPhase(): GamePhase {
        val thisPhase = table.phaseStack.pop()
        val upperPhase = table.phaseStack.peek()
        table.phaseStack.push(thisPhase)
        return upperPhase
    }

    override fun toString(): String {
        return logName ?: "匿名阶段"
    }
}
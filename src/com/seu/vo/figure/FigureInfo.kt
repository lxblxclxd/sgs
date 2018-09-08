package com.seu.vo.figure

import com.seu.vo.skill.Skill

class FigureInfo(val name:String,
                 val sex:String,
                 val country:String,
                 val initHp:Int,
                 val skill: List<Skill>,
                 val img:String? = null) {
}
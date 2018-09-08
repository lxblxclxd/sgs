package test.seu.biz.table.impl

import com.seu.biz.table.ITable
import com.seu.biz.table.impl.StandardTable
import org.junit.Ignore
import org.junit.Test

class StandardTableTest {

    @Test
    @Ignore
    fun startTest(){
        val table : ITable = StandardTable(2)
        table.start()
    }
}
package me.oogh.demo

/**
 * 工厂类
 * 生成数据
 */
class Factory {
    // companion object: 伴随对象，类似于Java中的Static，因此其中的方法可以使用类名直接调用
    companion object {
        fun generateData(): List<String> {
            return listOf("data1", "data2", "data3", "data4")
        }
    }
}

/**
 * 仓库类
 *  从工厂中拿到数据后，返回给商人
 */
class Repository {
    fun loadData(callback: (dataSet: List<String>) -> Unit) {
        callback.invoke(Factory.generateData())
    }
}

/**
 * 销售商
 *  从仓库中拿到数据后，进行销售
 */
class Seller {
    private val repository = Repository()
    fun sell() {
        repository.loadData { dataSet -> dataSet.forEach { println(it) } }
    }
}

/**
 * 运行，测试
 */
fun main(args: Array<String>) {
    val seller = Seller()
    seller.sell()
}
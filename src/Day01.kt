fun main() {
    fun part1(input: List<String>): Int {
        var prevLine = input[0].toInt()
        var count = 0
        for (i in 1 until input.size) {
            val tmpLine = input[i].toInt()
            if (tmpLine > prevLine)
                ++count
            prevLine = tmpLine
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var prevLine = 0
        var count = 0
        for (i in 0..2)
            prevLine += input[i].toInt()

        for (i in 1 until input.size) {
            var tmpLine = 0
            if (i + 2 < input.size) {
                for (j in 0..2)
                    tmpLine += input[i + j].toInt()

                if (tmpLine > prevLine)
                    ++count
                prevLine = tmpLine
            }
        }

        return count
    }

    val input1 = readInput("input11")
    println(part1(input1))
    println(part2(input1))
}

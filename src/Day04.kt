class Bingo {
    private val rows = mutableListOf<MutableList<Int>>()

    private val rowsDone = mutableListOf<MutableList<Boolean>>()
    private val columnsDone = mutableListOf<MutableList<Boolean>>()

    var columnInx = 0

    init {
        for (i in 1..5) {
            rowsDone.add(mutableListOf(false, false, false, false, false))
            columnsDone.add(mutableListOf(false, false, false, false, false))
        }
    }

    fun addRow(rowInput: String) {
        val row = rowInput.split(" ")
        val rowList = mutableListOf<Int>()
        for (num in row) {
            if (num != "") {
                rowList.add(num.toInt())
            }
        }
        rows.add(rowList)
    }

    fun isWin(): Boolean {
        for (row in rowsDone) {
            var rowDone = true
            for (done in row)
                if (!done) {
                    rowDone = false
                    break;
                }
            if (rowDone)
                return true
        }
        for (column in columnsDone) {
            var columnDone = true
            for (done in column)
                if (!done)
                    columnDone = false
            if (columnDone)
                return true
        }
        return false
    }

    fun checkNum(num: Int) {
        for (i in rows.indices)
            for (j in rows[i].indices) {
                if (rows[i][j] == num) {
                    rowsDone[i][j] = true
                    columnsDone[j][i] = true
                    return
                }
            }
    }

    fun getScore(): Int {
        var res = 0
        for (i in rowsDone.indices) {
            for (j in rowsDone[i].indices)
                if (!rowsDone[i][j]) {
                    res += rows[i][j]
                }
        }
        return res
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val bingos = mutableListOf<Bingo>()
        val numbers = mutableListOf<Int>()
        val firstLine = input[0].split(",")
        for (num in firstLine) {
            numbers.add(num.toInt())
        }
        for (i in 1 until input.size) {
            if (input[i] == "") {
                bingos.add(Bingo())
                for (j in 1 until 6)
                    bingos.last().addRow(input[i + j])
            }
        }
        for (num in numbers) {
            var sum = -1
            for (bingo in bingos) {
                bingo.checkNum(num)
                if (bingo.isWin()) {
                    println("${bingo.getScore()} : $num")
                    if (bingo.getScore() * num > sum)
                        sum = bingo.getScore() * num
                }
            }
            if (sum > -1)
                return sum
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        val bingos = mutableListOf<Bingo>()
        val numbers = mutableListOf<Int>()
        val firstLine = input[0].split(",")
        for (num in firstLine) {
            numbers.add(num.toInt())
        }
        for (i in 1 until input.size) {
            if (input[i] == "") {
                bingos.add(Bingo())
                for (j in 1 until 6)
                    bingos.last().addRow(input[i + j])
            }
        }
        val doneBingos = mutableListOf<Bingo>()
        for (num in numbers) {
            for (bingo in bingos) {
                bingo.checkNum(num)
                if (bingo.isWin()) {
                    println("${bingo.getScore()} : $num")
                    if (!doneBingos.contains(bingo))
                        doneBingos.add(bingo)

                    if (doneBingos.size == bingos.size)
                        return bingo.getScore() * num
                }
            }
        }

        return 0
    }

    val input1 = readInput("input4")
    println(part1(input1))
    println(part2(input1))
}

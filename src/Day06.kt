import kotlin.math.ceil

fun main() {
    fun part1(input: List<String>): Int {
        val splitted = input[0].split(",")
        val fishes = mutableListOf<Int>()
        for (split in splitted)
            fishes.add(split.toInt())

        var firstChild = 0
        for (i in 1 .. 80) {
            var newFish = 0
            for (j in fishes.indices) {
                if (fishes[j] == 0) {
                    fishes[j] = 6
                    if (j == 0) {
                        firstChild++
                    }
                    newFish++
                } else {
                    fishes[j]--
                }
            }
            for (j in 0 until newFish) {
                fishes.add(8)
            }
        }
        return fishes.size
    }

    val mapOfNums = HashMap<Int, Long>()

    fun getChild(life: Int): Long {
        if (mapOfNums.containsKey(life))
            return mapOfNums[life]!!
        else {
            var sum: Long = 0
            val firstChild = ceil(((256.0 - life) / 7.0)).toInt()
            for (i in 0 until firstChild) {
                sum += getChild(life + 1 + i * 7 + 8) + 1
            }
            mapOfNums[life] = sum
            return sum
        }
    }

    fun part2(input: List<String>): Long {
        val splitted = input[0].split(",")
        val fishes = mutableListOf<Int>()
        for (split in splitted)
            fishes.add(split.toInt())
        var sum: Long = fishes.size.toLong()
        for (fish in fishes) {
            sum += getChild(fish)
        }
        return sum
    }

    val input1 = readInput("input6")
    println(part1(input1))
    println(part2(input1))
}

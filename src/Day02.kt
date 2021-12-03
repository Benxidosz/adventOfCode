fun main() {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0

        for (line in input) {
            val splitted = line.split(" ")
            when (splitted[0]) {
                "forward" -> horizontal += splitted[1].toInt()
                "down" -> depth += splitted[1].toInt()
                "up" -> depth -= splitted[1].toInt()
            }
        }

        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        for (line in input) {
            val splitted = line.split(" ")
            when (splitted[0]) {
                "forward" -> {
                    horizontal += splitted[1].toInt()
                    depth += aim * splitted[1].toInt()
                }
                "down" -> aim += splitted[1].toInt()
                "up" -> aim -= splitted[1].toInt()
            }
        }

        return horizontal * depth
    }

    val input1 = readInput("input21")
    println(part1(input1))
    println(part2(input1))
}

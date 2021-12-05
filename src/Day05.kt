import kotlin.math.abs

data class Point(val coordinates: String) {
    var x: Int
    var y: Int
    init {
        val split = coordinates.split(",")
        x = split[0].toInt()
        y = split[1].toInt()
    }

    override fun toString(): String {
        return "$x,$y"
    }
}

data class Line(var p1: Point, var p2: Point) {
    init {
        if (p2.x < p1.x || (p1.x == p2.x && p2.y < p1.y)) {
            val tmpPoint = p2
            p2 = p1
            p1 = tmpPoint
        }
    }
    fun isValid(): Boolean = p1.x == p2.x || p1.y == p2.y

    fun isValid2() = p1.x == p2.x || p1.y == p2.y || (p1.x == p1.y && p2.x == p2.y) || (p2.x - p1.x == abs(p2.y - p1.y))

    fun getPoints(): List<Point> {
        val res = mutableListOf<Point>()

        if (p1.x == p2.x) {
            for (i in p1.y..p2.y)
                res.add(Point("${p1.x},$i"))
        }
        if (p1.y == p2.y) {
            for (i in p1.x..p2.x)
                res.add(Point("$i,${p1.y}"))
        }
        if (p2.x - p1.x == p1.y - p2.y) {
            val tmpPoint = Point(p1.toString())
            while (tmpPoint.toString() != p2.toString()) {
                res.add(Point(tmpPoint.toString()))
                tmpPoint.x++
                tmpPoint.y--
            }
            res.add(Point(tmpPoint.toString()))
        }
        if (p2.x - p1.x == p2.y - p1.y) {
            val tmpPoint = Point(p1.toString())
            while (tmpPoint.toString() != p2.toString()) {
                res.add(Point(tmpPoint.toString()))
                tmpPoint.x++
                tmpPoint.y++
            }
            res.add(Point(tmpPoint.toString()))
        }

        return res
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val lines = mutableListOf<Line>()
        for (line in input) {
            val points = line.split(" -> ")
            val line = Line(
                Point(points[0]),
                Point(points[1])
            )
            if (line.isValid())
                lines.add(line)
        }
        val mapOfPoint = HashMap<String, Int>()
        for (line in lines) {
            var list = line.getPoints()
            for (point in list) {
                val tmpKey = point.toString()
                mapOfPoint[tmpKey] = mapOfPoint[tmpKey]?.plus(1) ?: 1
            }
        }
        var count = 0
        for (key in mapOfPoint.keys) {
            if (mapOfPoint[key]!! > 1) {
                count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val lines = mutableListOf<Line>()
        for (line in input) {
            val points = line.split(" -> ")
            val line = Line(
                Point(points[0]),
                Point(points[1])
            )
            if (line.isValid2())
                lines.add(line)
        }
        val mapOfPoint = HashMap<String, Int>()
        for (line in lines) {
            var list = line.getPoints()
            for (point in list) {
                val tmpKey = point.toString()
                mapOfPoint[tmpKey] = mapOfPoint[tmpKey]?.plus(1) ?: 1
            }
        }
        var count = 0
        for (key in mapOfPoint.keys) {
            if (mapOfPoint[key]!! > 1) {
                count++
            }
        }
        return count
    }

    val input = readInput("input5")
    println(part1(input))
    println(part2(input))
}

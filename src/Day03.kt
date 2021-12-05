fun main() {
    fun part1(input: List<String>): Int {
        val countOnes = mutableListOf<Int>()
        val countZeros = mutableListOf<Int>()
        for (i in 0 until input[0].length) {
            countOnes.add(0)
            countZeros.add(0)
        }
        for (line in input) {
            for (i in line.indices) {
                if (line[i] == '0')
                    countZeros[i]++
                else
                    countOnes[i]++
            }
        }
        val gamma = mutableListOf<Int>()
        val epsilon = mutableListOf<Int>()
        for (i in 0 until countOnes.size) {
            gamma.add(if (countOnes[i] > countZeros[i]) {
                1
            } else {
                0
            })
            epsilon.add(if (countOnes[i] > countZeros[i]) {
                0
            } else {
                1
            })
        }

        var sumGamma = 0
        var sumEpsilon = 0
        for (i in 0 until gamma.size) {
            sumGamma += Math.pow(2.0, i.toDouble()).toInt() * gamma[gamma.size - 1 - i]
            sumEpsilon += Math.pow(2.0, i.toDouble()).toInt() * epsilon[epsilon.size - 1 - i]
        }

        println(sumGamma)
        println(sumEpsilon)

        return sumGamma * sumEpsilon
    }

    fun part2(input: List<String>): Int {
        val countOnes = mutableListOf<Int>()
        val countZeros = mutableListOf<Int>()
        for (i in 0 until input[0].length) {
            countOnes.add(0)
            countZeros.add(0)
        }
        var oxygen = mutableListOf<String>()
        oxygen.addAll(input)
        for (i in 0 until input[0].length) {
            for (i in 0 until input[0].length) {
                countOnes[i] = 0
                countZeros[i] = 0
            }
            for (line in oxygen) {
                for (i in line.indices) {
                    if (line[i] == '0')
                        countZeros[i]++
                    else
                        countOnes[i]++
                }
            }
            val tmpFilter = if (countOnes[i] >= countZeros[i]) {
                '1'
            } else {
                '0'
            }
            val tmpOxygen = mutableListOf<String>()
            for (line in oxygen) {
                if (line[i] == tmpFilter)
                    tmpOxygen.add(line)
            }
            print(tmpFilter)
            oxygen.clear()
            oxygen.addAll(tmpOxygen)
            if (oxygen.size == 1)
                break
        }
        var carbon = mutableListOf<String>()
        carbon.addAll(input)
        for (i in 0 until countOnes.size) {
            for (i in 0 until input[0].length) {
                countOnes[i] = 0
                countZeros[i] = 0
            }
            for (line in carbon) {
                for (i in line.indices) {
                    if (line[i] == '0')
                        countZeros[i]++
                    else
                        countOnes[i]++
                }
            }
            val tmpFilter = if (countOnes[i] >= countZeros[i]) {
                '0'
            } else {
                '1'
            }
            val tmpCarbon = mutableListOf<String>()
            for (line in carbon) {
                if (line[i] == tmpFilter)
                    tmpCarbon.add(line)
            }
            carbon.clear()
            carbon.addAll(tmpCarbon)
            if (carbon.size == 1)
                break
        }
        var sumOxygen = 0
        var sumCarbon = 0
        for (i in 0 until oxygen[0].length) {
            sumOxygen += Math.pow(2.0, i.toDouble()).toInt() * (oxygen[0][oxygen[0].length - 1 - i] - '0')
            sumCarbon += Math.pow(2.0, i.toDouble()).toInt() * (carbon[0][carbon[0].length - 1 - i] - '0')
        }

        println(oxygen)
        println(sumOxygen)
        println(carbon)
        println(sumCarbon)

        return sumOxygen * sumCarbon
    }

    val input1 = readInput("input31")
    println(part1(input1))
    println(part2(input1))
}

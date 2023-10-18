import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.RealMatrix

fun main() {
    // Матрица коэффициентов A
    val aArray = arrayOf(
        doubleArrayOf(3.0, 2.0, 14.0),
        doubleArrayOf(1.0, 2.0, 16.0),
        doubleArrayOf(4.0, 3.0, 5.0)
    )

    // Вектор непроизводственного потребления b
    val bArray = doubleArrayOf(11.0, 3.0, 20.0)

    // Вектор конечного продукта y
    val yArray = doubleArrayOf(25.0, 95.0, 50.0)

    // Создаем объекты RealMatrix из массивов
    val aMatrix: RealMatrix = MatrixUtils.createRealMatrix(aArray)
    val bMatrix: RealMatrix = MatrixUtils.createColumnRealMatrix(bArray)
    val yMatrix: RealMatrix = MatrixUtils.createColumnRealMatrix(yArray)

    // Вычисляем обратную матрицу A^(-1)
    val aInverse: RealMatrix = MatrixUtils.inverse(aMatrix)

    // Вычисляем разницу y - b
    val yMinusB: RealMatrix = yMatrix.subtract(bMatrix)

    // Умножаем A^(-1) на (y - b) для получения вектора x
    val xMatrix: RealMatrix = aInverse.multiply(yMinusB)

    // Извлекаем значения x1, x2, x3 из вектора xMatrix
    val xArray: DoubleArray = xMatrix.getColumn(0)

    // Выводим результат
    println("x1 = ${xArray[0]}")
    println("x2 = ${xArray[1]}")
    println("x3 = ${xArray[2]}")
}
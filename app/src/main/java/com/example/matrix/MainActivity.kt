package com.example.matrix

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.matrix.databinding.ActivityMainBinding // Update the import to use your generated binding class
import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.RealMatrix
import org.apache.commons.math3.linear.SingularMatrixException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Declare a binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // Set the content view to the root of the binding

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            // This block of code will be executed when the button is clicked
            // You can place your matrix calculation code here
            calculateMatrix()
        }
    }
    // Function to calculate the matrix
    private fun calculateMatrix() {
        // Now, you can access your EditText views using binding
        val editText1_1 = binding.row11
        val editText1_2 = binding.row12
        val editText1_3 = binding.row13
        val editText1_4 = binding.row14
        val editText1_5 = binding.row15
        val editText1_6 = binding.row16

        val editText2_1 = binding.row21
        val editText2_2 = binding.row22
        val editText2_3 = binding.row23
        val editText2_4 = binding.row24
        val editText2_5 = binding.row25
        val editText2_6 = binding.row26

        val editText3_1 = binding.row31
        val editText3_2 = binding.row32
        val editText3_3 = binding.row33
        val editText3_4 = binding.row34
        val editText3_5 = binding.row35
        val editText3_6 = binding.row36

        // Extracting values from EditText fields with null checks
        val value1_1 = editText1_1.text.toString().toDoubleOrNull() ?: 0.0
        val value1_2 = editText1_2.text.toString().toDoubleOrNull() ?: 0.0
        val value1_3 = editText1_3.text.toString().toDoubleOrNull() ?: 0.0
        val value1_4 = editText1_4.text.toString().toDoubleOrNull() ?: 0.0
        val value1_5 = editText1_5.text.toString().toDoubleOrNull() ?: 0.0
        val value1_6 = editText1_6.text.toString().toDoubleOrNull() ?: 0.0

        val value2_1 = editText2_1.text.toString().toDoubleOrNull() ?: 0.0
        val value2_2 = editText2_2.text.toString().toDoubleOrNull() ?: 0.0
        val value2_3 = editText2_3.text.toString().toDoubleOrNull() ?: 0.0
        val value2_4 = editText2_4.text.toString().toDoubleOrNull() ?: 0.0
        val value2_5 = editText2_5.text.toString().toDoubleOrNull() ?: 0.0
        val value2_6 = editText2_6.text.toString().toDoubleOrNull() ?: 0.0

        val value3_1 = editText3_1.text.toString().toDoubleOrNull() ?: 0.0
        val value3_2 = editText3_2.text.toString().toDoubleOrNull() ?: 0.0
        val value3_3 = editText3_3.text.toString().toDoubleOrNull() ?: 0.0
        val value3_4 = editText3_4.text.toString().toDoubleOrNull() ?: 0.0
        val value3_5 = editText3_5.text.toString().toDoubleOrNull() ?: 0.0
        val value3_6 = editText3_6.text.toString().toDoubleOrNull() ?: 0.0

        // Матрица коэффициентов A
        val aArray = arrayOf(
            doubleArrayOf(value1_1, value1_2, value1_3),
            doubleArrayOf(value2_1, value2_2, value2_3),
            doubleArrayOf(value3_1, value3_2, value3_3)
        )

        // Вектор непроизводственного потребления b
        val bArray = doubleArrayOf(value1_4, value2_4, value3_4)

        // Вектор конечного продукта y
        val yArray = doubleArrayOf(value1_6, value2_6, value3_6)

        // Создаем объекты RealMatrix из массивов
        val aMatrix: RealMatrix = MatrixUtils.createRealMatrix(aArray)
        val bMatrix: RealMatrix = MatrixUtils.createColumnRealMatrix(bArray)
        val yMatrix: RealMatrix = MatrixUtils.createColumnRealMatrix(yArray)

        try {
            // Вычисляем обратную матрицу A^(-1)
            val aInverse: RealMatrix = MatrixUtils.inverse(aMatrix)

            // Вычисляем разницу y - b
            val yMinusB: RealMatrix = yMatrix.subtract(bMatrix)

            // Умножаем A^(-1) на (y - b) для получения вектора x
            val xMatrix: RealMatrix? = aInverse?.multiply(yMinusB)

            if (xMatrix != null) {
                // Извлекаем значения x1, x2, x3 из вектора xMatrix
                val xArray: DoubleArray = xMatrix.getColumn(0)

                // Форматируем результат в строку
                val resultText = "Цех1 = ${xArray[0]}\nЦех2 = ${xArray[1]}\nЦех3 = ${xArray[2]}"

                // Отображаем результат в TextView using View Binding
                binding.resultTextView.text = resultText
            } else {
                // Handle the case where aInverse is null (e.g., due to a singular matrix)
                binding.resultTextView.text = "Matrix A is singular or not invertible"
            }
        } catch (e: SingularMatrixException) {
            // Handle the exception here, such as displaying an error message to the user
            // or logging the error for debugging purposes.
            e.printStackTrace() // You can replace this with appropriate error handling.
        }
    }
}

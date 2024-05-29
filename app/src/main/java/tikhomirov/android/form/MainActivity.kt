package tikhomirov.android.form

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tikhomirov.android.form.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val radioButtonPaid = binding.radioButtonPaid
        val salaryLinearLayout = binding.salaryLinearLayout
        radioButtonPaid.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                salaryLinearLayout.visibility = View.VISIBLE
            } else {
                salaryLinearLayout.visibility = View.INVISIBLE
            }
        }

         //Добавляем логику для скрытия элементов при открытии клавиатуры
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            // Создаем объект Rect, который будет хранить координаты видимого экрана
            val rect = android.graphics.Rect()
            // Получаем видимую область экрана и сохраняем её в объекте Rect
            binding.root.getWindowVisibleDisplayFrame(rect)
            // Получаем высоту всего корневого представления
            val screenHeight = binding.root.rootView.height
            // Вычисляем высоту клавиатуры как разницу между высотой экрана и нижней границей видимой области
            val keypadHeight = screenHeight - rect.bottom

            // Если высота клавиатуры больше 15% от общей высоты экрана, предполагаем, что клавиатура открыта
            if (keypadHeight > screenHeight * 0.15) {
                // Скрываем кнопку отправки формы, если клавиатура открыта
                binding.buttonSubmit.visibility = View.GONE
                // Скрываем текст копирайта, если клавиатура открыта
                binding.copyright.visibility = View.GONE
            } else { // Клавиатура закрыта
                // Показываем кнопку отправки формы, если клавиатура закрыта
                binding.buttonSubmit.visibility = View.VISIBLE
                // Показываем текст копирайта, если клавиатура закрыта
                binding.copyright.visibility = View.VISIBLE
            }
        }

    }
}
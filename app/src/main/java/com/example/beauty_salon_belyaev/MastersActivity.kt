package com.example.beauty_salon_belyaev

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MastersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MastersAdapter
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masters)

        recyclerView = findViewById(R.id.recyclerViewMasters)
        recyclerView.layoutManager = LinearLayoutManager(this)
        buttonBack = findViewById(R.id.buttonBack)

        buttonBack.setOnClickListener {
            finish()
        }

        val masters = listOf(
            Master(
                "Лебедев Тимур Георгиевич",
                "Специалист по стрижке и укладке. Работает в индустрии более 5 лет. Обладает опытом работы с различными типами волос и умеет использовать самые современные техники стрижки.",
                "Услуги по стрижке, укладке, также предоставляет консультации по уходу за волосами."
            ),
            Master(
                "Образумова Дарья Дмитриевна",
                "Высококвалифицированный мастер маникюра с опытом работы более 3 лет. Проводит услуги по маникюру, педикюру и разработке индивидуального дизайна ногтей.",
                "Маникюр, педикюр, гелевое покрытие, стильный дизайн ногтей, spa-процедуры для рук и ног."
            ),
            Master(
                "Мельников Сергей Павлович",
                "Косметолог с многолетним опытом работы в области уходовых процедур. Специализируется на уходе за лицом, предлагает индивидуальные программы по уходу за кожей.",
                "Уход за лицом, чистка, пилинг, массаж, услуги по коррекции овалов лица."
            ),
            Master(
                "Иванова Анастасия Кирилловна",
                "Мастер по укладке с большой любовью к своему делу. Имеет опыт работы в лучших салонах и умеет создавать как повседневные, так и вечерние прически.",
                "Косметология, укладка волос, создание вечерних и свадебных причесок, услуги по уходу за волосами."
            )
        )
        adapter = MastersAdapter(masters)
        recyclerView.adapter = adapter
    }
}
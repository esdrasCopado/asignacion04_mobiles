package mx.edu.itson.practica3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductosActivity : AppCompatActivity() {
    var menu: ArrayList<Product> = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val menuOption: String? = intent.getStringExtra("menuType")

        agregarProductos(menuOption)

        val imagen: ImageView = findViewById(R.id.imageView)

        when(menuOption){
            "Antojitos"->{
                imagen.setImageResource(R.drawable.antojitos)
            }
            "Especialidades"->{
                imagen.setImageResource(R.drawable.especialidades)
            }
            "Combinations"->{
                imagen.setImageResource(R.drawable.combinations)
            }
            "Tortas"->{
                imagen.setImageResource(R.drawable.tortas)
            }
            "Sopas"->{
                imagen.setImageResource(R.drawable.sopas)
            }
            "Drinks"->{
                imagen.setImageResource(R.drawable.drinks)
            }
        }

        val listview: ListView = findViewById(R.id.listView)
        val adaptador = AdaptadorProductos(this, menu)
        listview.adapter = adaptador
    }
    fun agregarProductos(option: String?) {
        when (option) {
            "Antojitos" -> {
                menu.add(Product("VALENTINE TACOS", R.drawable.valentine_tacos, "20 TACOS CHOOSE UP TO THREE MEATS. COME WIHT TOPPINGS ONION, CILANTRO, SALSA, AND RADISHES. 20 TACOS, SELECCIONE TRES CARNES. VIENE CON CEBOLLA CILANTRO, RABANOS Y SALSAS.", 49.00))
                menu.add(Product("Quesadillas", R.drawable.quesadillas, "Rellenas con su carne favorita, servidas con ensalada - Filled with your choice of meat, served with salad", 6.29))
                menu.add(Product("Huaraches", R.drawable.huaraches, "Tortilla gruesa con frijoles, tu carne favorita, lechuga, queso fresco y crema - Big thick tortilla with beans, your choice of meat, fresh cheese, and sour cream.", 11.49))
                menu.add(Product("Gringas", R.drawable.gringas, "Tortilla de harina con queso, carne al pastor y piña. Flour tortilla filled with cheese, marinated pork and pineapple.", 8.39))
                menu.add(Product("Sopes", R.drawable.sopes, "Tortilla gruesa cubierta de frijoles, tu carne favorita, lechuga, queso fresco y crema. Fried thick tortilla with beans, your choice of meat, lettuce, fresh cheese, and sour cream.", 3.99))
                menu.add(Product("Tostadas", R.drawable.tostadas, "Tortilla frita con frijoles, tu carne favorita, lechuga, queso fresco, crema y jitomate. Fried tortilla with beans, your choice of meat, lettuce, fresh cheese, sour cream and tomatoes.", 4.59))
            }
            "Especialidades" -> {
                menu.add(Product("Coctel Mixto", R.drawable.coctel_mixto, "shrimp, octopus, pico de gallo, avocado, lime and coctel sauce. served cold.", 21.99))
                menu.add(Product("Coctel De Camaron", R.drawable.coctel_de_camaron, "Shrimp cocktail", 16.49))
                menu.add(Product("Mojarra Frita", R.drawable.mojarra, "Tilapia frita servida con arroz, ensalada y tortillas - Fried tilapia served with rice, salad and tortillas", 15.99))
                menu.add(Product("Fajitas de Res", R.drawable.fajitaquesadilla, "Fajitas de res servidas con arroz, frijoles y ensalada - Beef fajitas served with rice, beans and salad", 17.99))
            }
            "Combinations" -> {
                menu.add(Product("Combination 1", R.drawable.combinationtaco, "One burrito, one taco, one enchilada", 10.99))
                menu.add(Product("Combination 2", R.drawable.cominationburros, "Two tacos, rice and beans", 9.99))
            }
            "Tortas" -> {
                menu.add(Product("Torta de Asada", R.drawable.tortamixta, "Torta de carne asada, con mayonesa, lechuga, jitomate y aguacate", 10.99))
                menu.add(Product("Torta Cubana", R.drawable.tortacubana, "La clásica torta cubana con todo", 13.99))
            }
            "Sopas" -> {
                menu.add(Product("Pozole", R.drawable.pozole, "Caldo tradicional a base de maíz", 9.99))
                menu.add(Product("Menudo", R.drawable.menudo, "Caldo de panza de res", 9.99))
            }
            "Drinks" -> {
                menu.add(Product("Aguas Frescas", R.drawable.caguamamichelada, "Horchata, Jamaica, Tamarindo", 2.99))
                menu.add(Product("Sodas Mexicanas", R.drawable.mexicanbeer, "Jarritos, Sidral Mundet, Sangria", 2.50))
            }
        }
    }
    private class AdaptadorProductos: BaseAdapter {
        var productos = ArrayList<Product>()
        var contexto: Context? = null

        constructor(contexto: Context, productos: ArrayList<Product>) {
            this.productos = productos
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return productos.size
        }

        override fun getItem(position: Int): Any {
            return productos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            val prod = productos[position] as Product
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.producto_view, null)

            val imagen: ImageView = vista.findViewById(R.id.producto_img)
            val nombre: TextView = vista.findViewById(R.id.producto_nombre)
            val desc: TextView = vista.findViewById(R.id.producto_desc)
            val precio: TextView = vista.findViewById(R.id.producto_precio)

            imagen.setImageResource(prod.image)
            nombre.text = prod.name
            desc.text = prod.description
            precio.text = "$${"%.2f".format(prod.price)}"

            return vista
        }
    }
}

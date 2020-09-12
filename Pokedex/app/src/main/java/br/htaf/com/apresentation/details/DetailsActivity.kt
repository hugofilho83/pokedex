package br.htaf.com.apresentation.details

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.htaf.com.R
import br.htaf.com.apresentation.base.BaseActivity
import br.htaf.com.apresentation.loadscreen.LoadSreean
import br.htaf.com.data.model.PokemonDetailsViewModel
import br.htaf.com.data.model.PokemonViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.InputStream
import java.net.URL


class DetailsActivity : BaseActivity() {

    private var numberPokemon : String? = "";
    private var namePokemon : String? = "";

    private lateinit var viewModel : PokemonDetailsViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        numberPokemon = intent.getStringExtra(NUMBER_POKEMON);
        namePokemon = intent.getStringExtra(NAME_POKEMON);

        setupToolbar(toolBar, "Pokémon : #".plus(numberPokemon), true);

        DownloadImageTask(imgPokemon).execute(numberPokemon);

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PokemonDetailsViewModel::class.java);

        viewModel.pokemonDetails.observe(this, {
            it?.let { pokemonDetails ->
                txtNameProkemon.text = pokemonDetails.name;
                txtWeight.text = pokemonDetails.weight.toString().plus("kg");
                txtHeight.text = pokemonDetails.height.toString().plus("m");
                txtHabilidades.text = pokemonDetails.abilities.joinToString ( ", " );
                txtType.text = pokemonDetails.types.joinToString ( ", " );
                txtMovimentos.text = pokemonDetails.skills.joinToString ( ", ") ;
            }
        });


        viewModel.viewFlipperPokemnDetails.observe(this, Observer {
            it?.let {viewFlipper->
                viewFlipperPokemonDetails.displayedChild = viewFlipper.first;

                viewFlipper.second?.let { errorMessengerResorceID->
                    txtMessegerErroDetails.text = getString(errorMessengerResorceID);
                }

            }
        })



        viewModel.getPokemonDetails(numberPokemon!!.toInt());



    }

    private class DownloadImageTask(bmImage: ImageView) : AsyncTask<String?, Void?, Bitmap?>() {
        var bmImage: ImageView = bmImage

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            val urlimage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + params[0] + ".png";
            //val urlimage = "https://pokeres.bastionbot.org/images/pokemon/" + params[0] + ".png";
            var image: Bitmap? = null;
            try {
                val imgStream: InputStream = URL(urlimage).openStream();
                image = BitmapFactory.decodeStream(imgStream);
            } catch (e: Exception) {
                Log.e("Error", e.message);
                e.printStackTrace();
            }

            return image;
        }
    }

    companion object{
        private const val NUMBER_POKEMON = "NUMBER_POKEMON";
        private const val NAME_POKEMON = "NAME_POKEMON";
        fun getStartIntent(context:Context, number:String, name: String): Intent{
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(NUMBER_POKEMON, number);
                putExtra(NAME_POKEMON, name);
            }
        }
    }
}
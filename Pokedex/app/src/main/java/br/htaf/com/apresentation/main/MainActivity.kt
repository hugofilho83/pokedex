package br.htaf.com.apresentation.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.htaf.com.R
import br.htaf.com.adapters.PokemonAdapter
import br.htaf.com.apresentation.base.BaseActivity
import br.htaf.com.apresentation.details.DetailsActivity
import br.htaf.com.apresentation.loadscreen.LoadSreean
import br.htaf.com.data.model.PokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel : PokemonViewModel;

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(toolBar, getString(R.string.app_name));

        viewModel =  ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PokemonViewModel::class.java);

        viewModel.pokemonsLiveData.observe(this, Observer {
            it?.let { pokemons ->
                with(recyclePokemons)
                {
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = PokemonAdapter(pokemons){pokemon ->
                        this@MainActivity.startActivity(
                            DetailsActivity.getStartIntent(this@MainActivity, pokemon.number, pokemon.name)
                        );
                    }
                }
            }
        });

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let {viewFlipper ->
                viewFlipperPokemon.displayedChild = viewFlipper.first;

                viewFlipper.second?.let { erroMessegerResId->
                    txtMensagemErro.text = getString(erroMessegerResId);
                }
            }
        })

        viewModel.GetPokemons();
    }




}
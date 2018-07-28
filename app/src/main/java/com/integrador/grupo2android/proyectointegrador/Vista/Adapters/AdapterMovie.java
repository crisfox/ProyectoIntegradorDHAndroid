package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.R;

import java.util.List;


/**
 * Created by DH on 18/5/2018.
 */

public class AdapterMovie extends RecyclerView.Adapter {

    private List<Movie> listaDeMovies;
    private EscuchadorDelAdapterMovie escuchadorDelAdapterMovie;
    private String tituloDeLaLista;
    private Context context;

    public AdapterMovie(EscuchadorDelAdapterMovie escuchadorDelAdapterMovie, List<Movie> listaDeMovies, String tituloDeLaLista) {
        this.escuchadorDelAdapterMovie = escuchadorDelAdapterMovie;
        this.listaDeMovies = listaDeMovies;
        this.tituloDeLaLista = tituloDeLaLista;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //obtenems el contexto del ViewGroup
        context = parent.getContext();
        //obtenemos el inflador del contexto.
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_audiovisual, parent, false);
        //metemos dentro del viewHolder al celda inflada
        MovieViewHolder movieViewHolder = new MovieViewHolder(celda);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //El onBindViewHolder tengo que unir la informacion de la celda en x posicion
        //conel viewhHolder.
        Movie movie = listaDeMovies.get(position);
        //Aca casteo al PersonajesViewHolder ya que estoy seguro que es de esa clase
        //ya que en el onCreateViewHolder cree un viewHolder de esa clase
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.asignarMovie(movie);
    }


    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.imageViewAudiovisualCartelera);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    escuchadorDelAdapterMovie.seleccionaronMovie(getAdapterPosition(), tituloDeLaLista);
                }
            });
        }


        public void asignarMovie(Movie movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w300/" + movie.getPoster_path()).into(imageViewMovie);

        }
    }

    public void setMovies(List<Movie> listaDeMoviesNuevos) {
        this.listaDeMovies.clear();
        if (listaDeMoviesNuevos != null) {
            this.listaDeMovies.addAll(listaDeMoviesNuevos);
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "Lista Null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return listaDeMovies.size();
    }

    public interface EscuchadorDelAdapterMovie {
        void seleccionaronMovie(Integer posicion, String tituloDeLaLista);
    }


}
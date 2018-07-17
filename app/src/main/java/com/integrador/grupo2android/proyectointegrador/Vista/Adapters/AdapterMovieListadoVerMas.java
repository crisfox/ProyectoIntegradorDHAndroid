package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DH on 18/5/2018.
 */

public class AdapterMovieListadoVerMas extends RecyclerView.Adapter {

    private List<Movie> listaDeMovie;
    private Context context;

    public AdapterMovieListadoVerMas(Context context) {
        this.listaDeMovie = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //obtenems el contexto del ViewGroup
        context = parent.getContext();
        //obtenemos el inflador del contexto.
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_audiovisual_gird, parent, false);
        //metemos dentro del viewHolder al celda inflada
        MovieViewHolder movieViewHolder = new MovieViewHolder(celda);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //El onBindViewHolder tengo que unir la informacion de la celda en x posicion
        //conel viewhHolder.
        Movie movie = listaDeMovie.get(position);
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
        }

        public void asignarMovie(Movie movie) {
            Glide.with(context).load(movie.getPoster_path()).into(imageViewMovie);
        }
    }

    @Override
    public int getItemCount() {
        return listaDeMovie.size();
    }

    public void setMovies(List<Movie> listaDeMovieNuevos){
        this.listaDeMovie.clear();
        this.listaDeMovie.addAll(listaDeMovieNuevos);
        notifyDataSetChanged();
    }

}
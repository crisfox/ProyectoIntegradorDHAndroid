package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.R;

import java.util.List;


/**
 * Created by DH on 18/5/2018.
 */

public class AdapterAudiovisual extends RecyclerView.Adapter {

    private List<Audiovisual> listaDeAudiovisuales;
    private EscuchadorDelAdapterAudiovisual escuchadorDelAdapterAudiovisual;
    private String tituloDeLaLista;

    public AdapterAudiovisual(EscuchadorDelAdapterAudiovisual escuchadorDelAdapterAudiovisual, List<Audiovisual> listaDeAudiovisuales, String tituloDeLaLista) {
        this.escuchadorDelAdapterAudiovisual = escuchadorDelAdapterAudiovisual;
        this.listaDeAudiovisuales = listaDeAudiovisuales;
        this.tituloDeLaLista = tituloDeLaLista;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //obtenems el contexto del ViewGroup
        Context context = parent.getContext();
        //obtenemos el inflador del contexto.
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_audiovisual, parent, false);
        //metemos dentro del viewHolder al celda inflada
        AudiovisualViewHolder audiovisualViewHolder = new AudiovisualViewHolder(celda);
        return audiovisualViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //El onBindViewHolder tengo que unir la informacion de la celda en x posicion
        //conel viewhHolder.
        Audiovisual audiovisual = listaDeAudiovisuales.get(position);
        //Aca casteo al PersonajesViewHolder ya que estoy seguro que es de esa clase
        //ya que en el onCreateViewHolder cree un viewHolder de esa clase
        AudiovisualViewHolder audiovisualViewHolder = (AudiovisualViewHolder) holder;
        audiovisualViewHolder.asignarAudiovisual(audiovisual);
    }

    private class AudiovisualViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewAudiovisual;

        public AudiovisualViewHolder(View itemView) {
            super(itemView);
            imageViewAudiovisual = itemView.findViewById(R.id.imageViewAudiovisualCartelera);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    escuchadorDelAdapterAudiovisual.seleccionaronAudiovisual(getAdapterPosition(), tituloDeLaLista);
                }
            });
        }

        public void asignarAudiovisual(Audiovisual audiovisual) {
            imageViewAudiovisual.setImageResource(audiovisual.getfoto());
        }
    }

    @Override
    public int getItemCount() {
        return listaDeAudiovisuales.size();
    }

    public interface EscuchadorDelAdapterAudiovisual{
        void seleccionaronAudiovisual(Integer posicion, String tituloDeLaLista);
    }


}
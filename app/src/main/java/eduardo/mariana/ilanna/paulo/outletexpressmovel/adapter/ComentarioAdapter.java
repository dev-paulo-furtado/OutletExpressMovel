package eduardo.mariana.ilanna.paulo.outletexpressmovel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eduardo.mariana.ilanna.paulo.outletexpressmovel.R;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.HomeActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.activity.ProdutoActivity;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.fragment.PesquisaFragment;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Categoria;
import eduardo.mariana.ilanna.paulo.outletexpressmovel.object.Comentario;

public class ComentarioAdapter extends RecyclerView.Adapter{
    public ProdutoActivity produtoActivity;
    public List<Comentario> comentarios;

    public ComentarioAdapter(ProdutoActivity produtoActivity, List<Comentario> comentarios) {
        this.produtoActivity = produtoActivity;
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(produtoActivity);
        View v = inflater.inflate(R.layout.itemlist_comentario,parent,false);
        return new CategoriasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        View v = holder.itemView;

        //preenche o nome da categoria
        TextView tvComentarioCliente = v.findViewById(R.id.tvComentarioCliente);
        tvComentarioCliente.setText(comentario.nome_cliente);

        TextView tvComentario = v.findViewById(R.id.tvComentario);
        tvComentario.setText(comentario.conteudo);

        TextView tvComentarioData = v.findViewById(R.id.tvComentarioData);
        tvComentarioData.setText(comentario.data);

        RatingBar rbComentarioAvaliacao = v.findViewById(R.id.rbComentarioAvaliacao);
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}

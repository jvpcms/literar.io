package com.example.agoravai.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agoravai.MainActivity;
import com.example.agoravai.R;
import com.example.agoravai.models.BookEntity;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<BookEntity> books;

    public BookAdapter(Context context, List<BookEntity> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        BookEntity book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView bookTitle;
        TextView bookSynopsis;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookSynopsis = itemView.findViewById(R.id.bookSynopsis);
        }

        public void bind(final BookEntity book) {
            bookTitle.setText(book.getTitle());
            bookSynopsis.setText(book.getSynopsis());

            // Configurando o clique no livro
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redirecionando para a página de review (MainActivity)
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("bookId", book.getId().toString());
                    context.startActivity(intent);
                }
            });
        }
    }
}

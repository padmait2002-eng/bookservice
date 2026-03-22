import React from 'react';
import './BookList.css';
import BookCard from './BookCard';

function BookList({ books, onDeleteBook, onUpdateBook }) {
  return (
    <div className="book-list-container">
      <h2>Books Collection</h2>
      {books.length === 0 ? (
        <p className="no-books">No books found. Add a new book to get started!</p>
      ) : (
        <div className="book-grid">
          {books.map(book => (
            <BookCard
              key={book.id}
              book={book}
              onDelete={onDeleteBook}
              onUpdate={onUpdateBook}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default BookList;


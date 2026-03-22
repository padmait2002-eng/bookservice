import React, { useState, useEffect } from 'react';
import './App.css';
import Header from './components/Header';
import BookList from './components/BookList';
import BookForm from './components/BookForm';
import bookService from './services/bookService';

function App() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadBooks();
  }, []);

  const loadBooks = async () => {
    try {
      setLoading(true);
      const response = await bookService.getAllBooks();
      setBooks(response.data);
    } catch (error) {
      console.error('Error loading books:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleAddBook = async (bookData) => {
    try {
      const response = await bookService.addBook(bookData);
      setBooks([...books, response.data]);
    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await bookService.deleteBook(id);
      setBooks(books.filter(book => book.id !== id));
    } catch (error) {
      console.error('Error deleting book:', error);
    }
  };

  const handleUpdateBook = async (bookData) => {
    try {
      const response = await bookService.updateBook(bookData);
      setBooks(books.map(book => book.id === bookData.id ? response.data : book));
    } catch (error) {
      console.error('Error updating book:', error);
    }
  };

  return (
    <div className="App">
      <Header />
      <div className="add-book-section">
        <BookForm onAddBook={handleAddBook} />
      </div>
      <div className="container">
        <div className="right-panel">
          {loading ? (
            <p className="loading">Loading books...</p>
          ) : (
            <BookList
              books={books}
              onDeleteBook={handleDeleteBook}
              onUpdateBook={handleUpdateBook}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default App;

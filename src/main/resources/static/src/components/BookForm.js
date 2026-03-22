import React, { useState } from 'react';
import './BookForm.css';

function BookForm({ onAddBook }) {
  const [formData, setFormData] = useState({
    name: '',
    price: ''
  });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'name') {
      if (/^\d/.test(value)) {
        setError('Book name should not start with a number');
      } else {
        setError('');
      }
    }

    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (error) {
      return;
    }
    if (formData.name.trim() && formData.price.trim()) {
      onAddBook({
        name: formData.name,
        price: parseFloat(formData.price)
      });
      setFormData({ name: '', price: '' });
      setError('');
    }
  };

  return (
    <div className="book-form-container">
      <h2>Add New Book</h2>
      <form onSubmit={handleSubmit} className="book-form">
        <div className="form-group">
          <input
            type="text"
            name="name"
            placeholder="Book Name"
            value={formData.name}
            onChange={handleChange}
            className={`form-input ${error ? 'input-error' : ''}`}
          />
          {error && <div className="error-message">{error}</div>}
        </div>
        <div className="form-group">
          <input
            type="number"
            name="price"
            placeholder="Price"
            value={formData.price}
            onChange={handleChange}
            className="form-input"
            step="0.01"
          />
        </div>
        <button type="submit" className="btn-add" disabled={!!error}>Add Book</button>
      </form>
    </div>
  );
}

export default BookForm;

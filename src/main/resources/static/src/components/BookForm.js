import React, { useState } from 'react';
import './BookForm.css';

function BookForm({ onAddBook }) {
  const [formData, setFormData] = useState({
    name: '',
    price: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.name.trim() && formData.price.trim()) {
      onAddBook({
        name: formData.name,
        price: parseFloat(formData.price)
      });
      setFormData({ name: '', price: '' });
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
            className="form-input"
          />
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
        <button type="submit" className="btn-add">Add Book</button>
      </form>
    </div>
  );
}

export default BookForm;


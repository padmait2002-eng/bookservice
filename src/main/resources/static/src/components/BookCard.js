import React, { useState } from 'react';
import './BookCard.css';

function BookCard({ book, onDelete, onUpdate }) {
  const [isEditing, setIsEditing] = useState(false);
  const [error, setError] = useState('');
  const [editData, setEditData] = useState({
    name: book.name,
    price: book.price
  });

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    if(name === 'name') {
      if (/^\d/.test(value)) {
        setError('Book name should not start with a number');
      } else {
        setError('');
      }
    }
    setEditData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSave = () => {
    if(error) {
        return;
    }
    onUpdate({
      id: book.id,
      name: editData.name,
      price: parseFloat(editData.price)
    });
    setIsEditing(false);
    setError('');
  };

  const handleCancel = () => {
    setEditData({ name: book.name, price: book.price });
    setIsEditing(false);
  };

  return (
    <div className="book-card">
      {isEditing ? (
        <div className="card-edit">
          <input
            type="text"
            name="name"
            value={editData.name}
            onChange={handleEditChange}
            className={`edit-input ${error ? 'input-error' : ''}` }
          />
          {error && <div className="error-message">{error}</div>}
          <input
            type="number"
            name="price"
            value={editData.price}
            onChange={handleEditChange}
            className="edit-input"
            step="0.01"
          />
          <div className="card-actions">
            <button className="btn-save" disabled={!!error} onClick={handleSave}>Save</button>
            <button className="btn-cancel" onClick={handleCancel}>Cancel</button>
          </div>
        </div>
      ) : (
        <div className="card-content">
          <h3>{book.name}</h3>
          <p className="price">₹{book.price.toFixed(2)}</p>
          <div className="card-actions">
            <button className="btn-edit" onClick={() => setIsEditing(true)}>Edit</button>
            <button className="btn-delete" onClick={() => onDelete(book.id)}>Delete</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default BookCard;


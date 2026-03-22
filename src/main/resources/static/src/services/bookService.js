import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const bookService = {
  getAllBooks: () => {
    return axios.get(`${API_BASE_URL}/findAllBooks`);
  },

  addBook: (book) => {
    return axios.post(`${API_BASE_URL}/addBook`, book);
  },

  deleteBook: (id) => {
    return axios.delete(`${API_BASE_URL}/delete/${id}`);
  },

  updateBook: (book) => {
    return axios.put(`${API_BASE_URL}/update`, book);
  }
};

export default bookService;


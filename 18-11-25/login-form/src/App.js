
// const App=() =>{
//   useEffect(()=>{
//     fetch('http://jsonplaceholder.typicode.com/todos')
//     .then(response => response.json()).then(json => console.log(json));},[]);
  


//   return (
//      <div className="App">
//       <h1>Check the console for fetched data</h1>
//     </div>
//   );
// }
import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';

function Login() {
  const [form, setForm] = useState({ email: "", password: "" });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("https://jsonplaceholder.typicode.com/posts", form)
  .then(res => console.log(res.data))
  .catch(err => console.log(err));
  };

  return (
    <div className="App">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input 
          type="email" 
          name="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
        />
        <input 
          type="password" 
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}


function App() {
  return (
    <div className="App">
      <Login />
    </div>
  );
}

export default App;

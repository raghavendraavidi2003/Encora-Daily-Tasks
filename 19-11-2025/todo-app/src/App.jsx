import { useState } from 'react'
import './App.css'

function App() {
  const [tasks, setTask] = useState([])
  const [text, setText] = useState("")

  const addTask = () => {
    if (text.trim() === "") {
      alert("Please enter task!")
      return
    }

    const newTask = {
      id: Date.now(),
      text: text,
      completed: false
    }
    
    setTask([...tasks, newTask])
    setText("")
  }

  const toggleComplete = (id) => {
    setTask(tasks.map(task =>
      task.id === id 
        ? { ...task, completed: !task.completed }
        : task
    ))
  }

  const deleteTask = (id) => {
    setTask(tasks.filter(task => task.id !== id))
  }

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      addTask()
    }
  }

  return (
    <div className='app'>
      <div className='todo-container'>
        <h1>My Todo List</h1>
        
        <div className='input-section'>
          <input 
            type="text" 
            placeholder='Enter a new task' 
            value={text} 
            onChange={(e) => setText(e.target.value)}
            onKeyPress={handleKeyPress}
            className='task-input'
          />
          <button onClick={addTask} className='add-button'>
            Add Task
          </button>
        </div>

        <div className='task-stats'>
          <p>Total: {tasks.length}</p>
          <p>Completed: {tasks.filter(t => t.completed).length}</p>
          <p>Pending: {tasks.filter(t => !t.completed).length}</p>
        </div>

        <ul className='task-list'>
          {tasks.length === 0 ? (
            <p className="empty-message">No tasks yet. Add one to get started.</p>
          ) : (
            tasks.map((task) => (
              <li 
                key={task.id} 
                className={task.completed ? "task-item completed" : "task-item"}
              >
                <div className="task-content">
                  <input
                    type="checkbox"
                    checked={task.completed}
                    onChange={() => toggleComplete(task.id)}
                    className="checkbox"
                  />
                  <span className="task-text">{task.text}</span>
                </div>
                <button 
                  onClick={() => deleteTask(task.id)}
                  className="delete-button"
                >
                  Delete
                </button>
              </li>
            ))
          )}
        </ul>
      </div>
    </div>
  )
}

export default App

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import React from 'react'
import './styles/Layout.css'
import './styles/Home.css'
import './styles/Cards.css'
import './styles/Tables.css'
import './styles/Forms.css'


createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)

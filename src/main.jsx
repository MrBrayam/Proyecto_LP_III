import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import './tienda.css'
import './tienda_auth.css'
import './tienda_checkout.css'
import './tienda_success.css'
import './admin.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)

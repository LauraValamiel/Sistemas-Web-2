import './App.css'
import './index.css'
import StoreProvider from './components/Provider'
import { AppRoutes } from './routes'
import { Link, Outlet } from 'react-router-dom'
import { Sidebar } from './components/layout/Sidebar'

function App() {

  return (
    <StoreProvider>
      <AppRoutes />
    </StoreProvider>
  )
}

export default App

export function DefaultLayout() {
    return (
        <div className="layout-container">
          <Sidebar />
            <main className="layout-content">
                <Outlet />
            </main>
        </div>
    )
}
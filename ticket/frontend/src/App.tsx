import './App.css'
import StoreProvider from './components/Provider'
import { AppRoutes } from './routes'

function App() {

  return (
    <StoreProvider>
      <AppRoutes />
    </StoreProvider>
  )
}

export default App

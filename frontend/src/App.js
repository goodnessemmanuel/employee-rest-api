import './App.css';
import HeaderComponent from './components/common/HeaderComponent';
import FooterComponent from './components/common/FooterComponent';
import { Outlet } from 'react-router-dom';
import AuthProvider from './security/AuthContext';

function App() {
  return (
    <div className='App'>
      <AuthProvider>
        <HeaderComponent />
        <main id="content">
            <Outlet />
        </main>
        <FooterComponent />
      </AuthProvider>
  </div>
  );
}

export default App;

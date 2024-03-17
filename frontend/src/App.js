import "./App.css";
import { LogInView } from "./pages/LoginView";
import { SignUpView } from "./pages/SignUpView";
import { AuthProvider } from "./contexts/AuthContext";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Dashboard } from "./pages/Dashboard";
import PersonalProfile from "./pages/PersonalProfile";
import RequireAuth from "./components/RequireAuth";

function App() {
  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="/login" element={<LogInView />} />
            <Route path="/signup" element={<SignUpView />} />
            <Route path="/" element={<Dashboard />} />
            <Route
              path="/personalProfile"
              element={
                <RequireAuth>
                  <PersonalProfile />
                </RequireAuth>
              }
            />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  );
}

export default App;

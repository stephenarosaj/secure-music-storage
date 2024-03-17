import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";

const RequireAuth = ({ children }) => {
  const { user } = useAuth();
  if (!user) {
    // User is not authenticated, redirect to login page
    return <Navigate to="/" />;
  }
  return children;
};

export default RequireAuth;

import React, { useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { Button, Alert } from "react-bootstrap";

export default function PersonalProfile() {
  const [error, setError] = useState("");
  const { user, logOut } = useAuth();

  const handleLogOut = async () => {
    try {
      await logOut();
    } catch (e) {
      let message;
      if (e instanceof Error) {
        message = e.message;
      } else {
        message = String(e);
      }
      setError(message);
    }
  };

  return (
    <>
      <div style={{ width: "400px" }}>
        {error && <Alert variant="danger">{error}</Alert>}
      </div>
      <div>{JSON.stringify(user)}</div>
      <div style={{ marginTop: "40px" }}>
        <Button
          style={{ width: "240px" }}
          colorScheme={"red"}
          onClick={handleLogOut}
        >
          Log Out
        </Button>
      </div>
    </>
  );
}

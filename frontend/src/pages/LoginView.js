import React, { useRef, useState } from "react";
import { Container, Card, Badge, Form, Button, Alert } from "react-bootstrap";
import { useAuth } from "../contexts/AuthContext";
import { Link, useNavigate } from "react-router-dom";
import { NavbarNoLogin } from "../components/NavBarUnLogin";
import { Footer } from "../components/Footer";

export const LogInView = React.memo(function LogInView() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const navigate = useNavigate();

  const { signUp, user, logIn } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password.length < 6) {
      setError("Password should be at least 6 digits!");
      return;
    }
    try {
      setError("");
      setLoading(true);
      await logIn(email, password);
      navigate("/personalProfile");
    } catch (e) {
      let message;
      if (e instanceof Error) {
        message = e.message;
      } else {
        message = String(e);
      }
      setError(message);
    }
    setLoading(false);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  return (
    <>
      <NavbarNoLogin></NavbarNoLogin>
      <Container
        className="d-flex align-items-center justify-content-center "
        style={{ maxWidth: "100vw", height: "84.5vh" }}
      >
        <div className="w-100" style={{ maxWidth: "600px" }}>
          <Card>
            <Card.Body>
              <div className="text-center"></div>

              <h1 className="text-center mb-4">Log In</h1>
              <p className="text-center">
                Welcome back to{" "}
                <Badge style={{ fontSize: "16px" }} bg="info">
                  Secure Music Storage
                </Badge>{" "}
                !{" "}
              </p>
              {error && <Alert variant="danger">{error}</Alert>}
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" id="email">
                  <Form.Label>
                    <b>Email</b>
                  </Form.Label>
                  <Form.Control
                    type="email"
                    value={email}
                    ref={emailRef}
                    onChange={handleEmailChange}
                    required
                  ></Form.Control>
                  <Form.Text className="text-muted">
                    We&apos;ll never share your email with anyone else.
                  </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" id="password">
                  <Form.Label>
                    <b>Password</b>
                  </Form.Label>
                  <Form.Control
                    type="password"
                    value={password}
                    ref={passwordRef}
                    onChange={handlePasswordChange}
                    required
                  ></Form.Control>
                </Form.Group>
                <Button disabled={loading} className="w-100" type="submit">
                  Log In
                </Button>
              </Form>
            </Card.Body>
          </Card>
          <div className="w-100 text-center mt-2">
            Need an account ? <Link to={"/signup"}>Sign Up !</Link>
          </div>
        </div>
      </Container>
      <Footer></Footer>
    </>
  );
});

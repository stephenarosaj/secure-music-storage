import React, { useRef, useState } from "react";
import { Container, Card, Badge, Form, Button, Alert } from "react-bootstrap";
import { useAuth } from "../contexts/AuthContext";
import { Link, useNavigate } from "react-router-dom";
import { NavbarNoLogin } from "../components/NavBarUnLogin";
import { Footer } from "../components/Footer";

export const SignUpView = React.memo(function SignUpView() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const passwordConfirmRef = useRef(null);
  const navigate = useNavigate();

  const { signUp, user } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== passwordConfirm) {
      setError("Please enter same password!");
      return;
    }

    if (password.length < 6) {
      setError("Password should be at least 6 digits!");
      return;
    }

    try {
      setError("");
      setLoading(true);
      const res = await signUp(email, password);
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

  const handlePasswordConfirmChange = (event) => {
    setPasswordConfirm(event.target.value);
  };

  return (
    <>
      <NavbarNoLogin></NavbarNoLogin>
      <Container
        className="d-flex align-items-center justify-content-center "
        style={{ height: "95.5vh", maxWidth: "100vw" }}
      >
        <div className="w-100" style={{ maxWidth: "600px" }}>
          <Card>
            <Card.Body>
              <div className="text-center"></div>
              <h1 className="text-center mb-4">Sign Up </h1>
              <p className="text-center">
                Start saving your music{" "}
                <Badge style={{ fontSize: "16px" }} bg="success">
                  Securely
                </Badge>{" "}
                in{" "}
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
                <Form.Group className="mb-3" id="password-confirm">
                  <Form.Label>
                    <b>Password Comfirmation</b>
                  </Form.Label>
                  <Form.Control
                    type="password"
                    value={passwordConfirm}
                    ref={passwordConfirmRef}
                    onChange={handlePasswordConfirmChange}
                    required
                  ></Form.Control>
                </Form.Group>
                <Button className="w-100" type="submit">
                  Sign Up!
                </Button>
              </Form>
            </Card.Body>
          </Card>
          <div className="w-100 text-center mt-2">
            Already have an account ? <Link to={"/login"}>Log In !</Link>
          </div>
        </div>
      </Container>
      <Footer></Footer>
    </>
  );
});

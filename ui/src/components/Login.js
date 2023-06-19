import { useState } from "react";
import Modal from "./Modal";
import axios from "axios";
import config from "../config.json";

export default function Login(props) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const authenticate = async () => {
    setLoading(true);
    const data = {
      email: email,
      password: password,
    };
    axios
      .post(config.API_BASE_URI + "auth/login", data)
      .then((response) => {
        if (!!response.data.token) {
          localStorage.setItem("api-token", response.data.token);
          localStorage.setItem("display_name", response.data.display_name);
          window.location.reload();
        } else {
          localStorage.removeItem("api-token");
        }
      })
      .catch((error) => {
        setError(error.response.data.message ?? "Unknown error.");
      })
      .finally(() => setLoading(false));
  };

  const content = () => {
    return (
      <>
        <div className="mt-20">
          {!!error && (
            <div className="py-1 w-full text-xs font-semibold rounded-md uppercase bg-orange-400 text-white text-center">
              {error}
            </div>
          )}
          <input
            type="email"
            placeholder="Email"
            onInput={(e) => setEmail(e.target.value)}
            className="mt-2 w-full rounded-md border-gray-200 shadow-sm sm:text-sm"
          />
          <input
            type="password"
            placeholder="Password"
            onInput={(e) => setPassword(e.target.value)}
            className="mt-4 w-full rounded-md border-gray-200 shadow-sm sm:text-sm"
          />
          <div className="flex justify-center mt-4">
            <button
              onClick={authenticate}
              className={
                "bg-red-500 px-10 py-1.5 text-sm rounded-md text-white " +
                (loading && " animate-pulse")
              }
              disabled={loading}
            >
              Login
            </button>
          </div>
        </div>
      </>
    );
  };

  return <Modal title="Login to your account" body={content()} />;
}

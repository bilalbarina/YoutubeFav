import Login from "./components/Login";
import Header from "./components/Header";
import Videos from "./components/Videos";

function App() {
  return (
    <>
      {!localStorage.getItem("api-token") ? (
        <Login /> 
      ) : (
        <>
          <Header />
          <div className="container max-w-xl py-16 px-6 md:px-0">
            <div className="flex flex-row justify-around">
              <div className="w-full">
                <Videos />
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}

export default App;

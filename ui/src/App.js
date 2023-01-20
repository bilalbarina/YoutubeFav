import Videos from "./components/Videos";

function App() {
  return (
    <div className="container max-w-xl py-24 px-6 md:px-0">
      <div className="flex flex-row justify-around">
        <div className="w-full">
          <Videos />
        </div>
      </div>
    </div>
  );
}

export default App;

export default function Header(props) {
  return (
    <>
      <header>
        <div className="flex justify-between p-6 px-14">
          <div> YouMarize </div>
          <div>
            <span className="text-sm font-semibold">
              Welcome, {localStorage.getItem("display_name")}!
            </span>
          </div>
        </div>
      </header>
    </>
  );
}

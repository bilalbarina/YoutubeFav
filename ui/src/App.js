import axios from "axios";
import { useEffect, useState } from "react";
import config from "./config.json";

function App() {
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    axios.get(config.YOUTUBE_BASE_URI, {
      params: {
        part: "snippet",
        q: "tanger",
        key: config.YOUTUBE_API_KEY,
      },
    }).then((response) => {
      setVideos(response.data.items)
    });
  }, []);

  return (
    <div className="">
      <ul>
        {videos.map((video) => (
          <li> {video.snippet.title} </li>
        ))}
      </ul>
    </div>
  );
}

export default App;

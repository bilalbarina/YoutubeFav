import { Component } from "react";
import axios from "axios";
import config from "../config.json";
import Loading from "./Loading";
import Favorites from "./Favorites";
import Spinner from "./Spinner";
import VideoPlayer from "./VideoPlayer";

class Videos extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: false,
      searchQuery: "",
      videos: [],
      favorites: [],
      showFavorites: false,
      addingToFavorites: [],
      videoPlayer: false,
      videoId: null,
    };

    this.handleSearch = this.handleSearch.bind(this);
    this.handleAddFavorite = this.handleAddFavorite.bind(this);
    this.handleRemoveFavorite = this.handleRemoveFavorite.bind(this);
  }

  componentDidMount() {
    this.getFavorites();
    this.search("Tanger");
  }

  handleAddFavorite = async (
    videoId,
    videoTitle,
    videoDescription,
    videoThumb
  ) => {
    this.setState({
      addingToFavorites: [...this.state.addingToFavorites, videoId],
    });
    await Favorites.add(videoId, videoTitle, videoDescription, videoThumb);
    await this.getFavorites();
    this.setState({
      addingToFavorites: this.state.addingToFavorites.filter(
        (item) => item != videoId
      ),
    });
  };

  handleRemoveFavorite = async (videoId) => {
    this.setState({
      addingToFavorites: [...this.state.addingToFavorites, videoId],
    });
    await Favorites.remove(videoId);
    await this.getFavorites();
    this.setState({
      addingToFavorites: this.state.addingToFavorites.filter(
        (item) => item != videoId
      ),
    });
  };

  VideoItem = (props) => {
    return (
      <>
        <div className="relative rounded-2xl bg-gradient-to-r from-pink-500 via-red-500 to-yellow-500 p-1 shadow">
          <div
            className="flex flex-row rounded-xl bg-white cursor-pointer"
            onClick={props.handleOnClick}
            // href={"https://www.youtube.com/watch?v=" + props.videoId}
          >
            <div className="p-0.5 w-1/2">
              <img className="rounded-xl" src={props.thumb} />
            </div>
            <div className="p-3 w-1/2">
              <h3 className="text-sm font-semibold text-gray-900 pr-4">
                {props.title}
              </h3>
              <p className="mt-2 text-xs text-gray-500 ">
                {props.description.length > 90
                  ? props.description.substring(0, 90) + "..."
                  : props.description}
              </p>
            </div>
          </div>
          <div
            className="absolute top-0 right-0 p-2 cursor-pointer"
            type="button"
            onClick={() =>
              props.favorited
                ? this.handleRemoveFavorite(props.videoId)
                : this.handleAddFavorite(
                    props.videoId,
                    props.title,
                    props.description,
                    props.thumb
                  )
            }
          >
            {this.state.addingToFavorites.includes(props.videoId) ? (
              <Spinner />
            ) : props.favorited ? (
              <Favorites.FavoritedIcon />
            ) : (
              <Favorites.FavIcon />
            )}
          </div>
        </div>
      </>
    );
  };

  getFavorites = async () => {
    return axios.get(config.API_BASE_URI + "favorite").then((response) => {
      this.setState({ favorites: response.data.data });
    });
  };

  handleSearch = (e) => {
    e.preventDefault();
    this.search(this.state.searchQuery);
  };

  search = (query) => {
    this.setState({ loading: true });
    axios
      .get(config.YOUTUBE_BASE_URI, {
        params: {
          q: query,
          part: "snippet",
          maxResults: 20,
          regionCode: "MA",
          type: "video",
          key: config.YOUTUBE_API_KEY,
        },
      })
      .then((response) => {
        this.setState({ loading: false });
        this.state.videos = response.data.items;
      });
  };

  render() {
    return (
      <>
        {this.state.videoPlayer && <VideoPlayer videoId={this.state.videoId} closeModal={() => this.setState({videoPlayer: false})} />}
        <Favorites
          showModal={this.state.showFavorites}
          closeModal={() => this.setState({ showFavorites: false })}
          body={this.state.favorites.map((video) => (
            <this.VideoItem
              videoId={video.video_id}
              title={video.video_title}
              description={video.video_title}
              thumb={video.video_thumb}
              favorited={true}
              handleOnClick={() =>
                this.setState({
                  showFavorites: false,
                  videoPlayer: true,
                  videoId: video.video_id,
                })
              }
            />
          ))}
        />
        <div className="flex flex-col">
          <div className="inline-flex items-center justify-around">
            <div className="relative w-3/4">
              <form onSubmit={this.handleSearch}>
                <input
                  type="text"
                  // placeholder="Search on YouTube"
                  placeholder="Tanger"
                  onInput={(e) =>
                    this.setState({ searchQuery: e.target.value })
                  }
                  className="w-full rounded-md border-gray-200 pr-10 shadow-sm sm:text-sm"
                />
              </form>
              <span className="pointer-events-none absolute inset-y-0 right-0 grid w-10 place-content-center text-gray-500">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                  className="w-5 h-5"
                >
                  <path
                    fillRule="evenodd"
                    d="M9 3.5a5.5 5.5 0 100 11 5.5 5.5 0 000-11zM2 9a7 7 0 1112.452 4.391l3.328 3.329a.75.75 0 11-1.06 1.06l-3.329-3.328A7 7 0 012 9z"
                    clipRule="evenodd"
                  />
                </svg>
              </span>
            </div>
            <div className="cursor-pointer">
              <div onClick={() => this.setState({ showFavorites: true })}>
                <Favorites.FavoritedIcon />
              </div>
            </div>
          </div>
          <div className="mt-8 px-12">
            {this.state.loading ? (
              <div className="flex justify-center mt-8 w-full">
                <Loading />
              </div>
            ) : (
              <div className="space-y-4">
                {this.state.videos.map((video) => (
                  <this.VideoItem
                    videoId={video.id.videoId}
                    title={video.snippet.title}
                    description={video.snippet.description}
                    thumb={video.snippet.thumbnails.high.url}
                    favorited={this.state.favorites.find(
                      (favorite) => favorite.video_id == video.id.videoId
                    )}
                    handleOnClick={() =>
                      this.setState({
                        videoPlayer: true,
                        videoId: video.id.videoId,
                      })
                    }
                  />
                ))}
              </div>
            )}
          </div>
        </div>
      </>
    );
  }
}

export default Videos;

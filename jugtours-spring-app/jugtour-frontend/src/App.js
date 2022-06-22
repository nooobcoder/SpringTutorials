import './App.css';
import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import GroupList from "./GroupList";
import Home from "./Home";

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/groups' exact={true} element={<GroupList/>}/>
      </Routes>
    </Router>
  );
}

export default App;

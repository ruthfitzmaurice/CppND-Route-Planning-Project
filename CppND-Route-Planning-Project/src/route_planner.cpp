#include "route_planner.h"
#include <algorithm>

RoutePlanner::RoutePlanner(RouteModel &model, float start_x, float start_y, float end_x, float end_y): m_Model(model) {
    start_x *= 0.01;
    start_y *= 0.01;
    end_x *= 0.01;
    end_y *= 0.01;
    start_node = &m_Model.FindClosestNode(start_x, start_y);
    end_node = &m_Model.FindClosestNode(end_x,end_y);
}

float RoutePlanner::CalculateHValue(RouteModel::Node const *node) {
    return (node->distance(*end_node));
}

void RoutePlanner::AddNeighbors(RouteModel::Node *current_node) {
    current_node->FindNeighbors();  //Using FindNeighbours method defined in route_model.cpp which adds
                                    // neighbouring nodes to this current nodes neighbour vector 
    for (auto neighbour : current_node->neighbors) {
        neighbour->parent = current_node;
        neighbour->g_value = current_node->g_value + current_node->distance(*neighbour);
        neighbour->h_value = CalculateHValue(neighbour);
        open_list.push_back(neighbour);
        neighbour->visited = true;
    }
}


RouteModel::Node *RoutePlanner::NextNode() {
   std::sort(open_list.begin(), open_list.end(),[] (const auto &_1st, const auto &_2nd) {return _1st->h_value + _1st->g_value < _2nd->h_value + _2nd->g_value;});
   RouteModel::Node *lowest_pointer;   
   lowest_pointer = open_list[0];
   open_list.erase(open_list.begin());
   return lowest_pointer;
}

std::vector<RouteModel::Node> RoutePlanner::ConstructFinalPath(RouteModel::Node *current_node) {
    distance = 0.0f;
    std::vector<RouteModel::Node> path_found;
    while (current_node != start_node) {

        path_found.push_back(*current_node);    //Add node to path
        distance += current_node->distance(*current_node->parent);  // for each node add the dist from node to parent
        current_node = current_node->parent;   // Move through the chain by updateing current node to its parent
    }
    path_found.push_back(*start_node);         // Add start node to end of path after while loop is no longer true
    std::reverse(path_found.begin(),path_found.end());  // Get in corrent format i.e. start node at the beginning
    distance *= m_Model.MetricScale(); // Multiply the distance by the scale of the map to get meters.
    //std::cout<< "Length of path: " << path_found.size() << "\n";
    return path_found;
}
void RoutePlanner::AStarSearch() {
    RouteModel::Node *current_node = nullptr; 
    current_node = start_node;
    open_list.push_back(current_node);
    current_node->visited =true;   
    //open_list.push_back(start_node); //add start node to open list
    //start_node->visited=true; //set start node visited to true 
    while(open_list.size()>0){
        //current_node = NextNode(); //sort open list and return the next node
        if(current_node->distance(*end_node)==0.0){ // if the current node is the end node, then call the construct final path method and return
            //std::cout <<"Current nodes X: "<< current_node->x <<"Current nodes Y: " << current_node->y << "\n";
            //std::cout <<"END nodes X: "<< end_node->x <<"END nodes Y: " << end_node->y << "\n";
            m_Model.path = ConstructFinalPath(current_node);  // Update m_Model path attrubte to the path found
          
        } else {
            AddNeighbors(current_node);    // If current node is not the end node, add neighbours with current node
            //current_node = NextNode(); //sort open list and return the next node
        }

        current_node = NextNode(); //sort open list and return the next node
    }
}
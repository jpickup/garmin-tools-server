# --- Stage 1: Build the Vue app ---
FROM node:25-alpine3.21 AS build
WORKDIR /app

# Accept backend URI as a build argument (Vite bakes it into the JS bundle)
ARG VITE_BACKEND_URI
ENV VITE_BACKEND_URI=${VITE_BACKEND_URI}

# Install dependencies separately for better caching
COPY package*.json ./
RUN npm install

# Copy the application code
COPY . .

# Build the production files
RUN npm run build

# --- Stage 2: Serve the built files with NGINX ---
FROM docker.io/library/nginx:alpine
# Remove default static assets and replace with our Vue build
RUN rm -rf /usr/share/nginx/html/*

# Copy built app from previous stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

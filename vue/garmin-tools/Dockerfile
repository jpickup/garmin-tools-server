# --- Stage 1: Build the Vue app ---
FROM node:25-alpine3.21 AS build
WORKDIR /app

# Install dependencies separately for better caching
COPY package*.json ./
RUN npm install

# Copy the application code
COPY . .

# Build the production files
RUN npm run build

# --- Stage 2: Serve the built files with NGINX ---
FROM nginx:alpine
# Remove default static assets and replace with our Vue build
RUN rm -rf /usr/share/nginx/html/*

# Copy built app from previous stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
